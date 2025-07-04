package com.bomjtrader.server.crypto

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.JWSSigner
import com.nimbusds.jose.crypto.ECDSASigner
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import jakarta.annotation.PostConstruct
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.openssl.PEMParser
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import java.io.StringReader
import java.net.URI
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.Security
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.time.Instant

@Service
class JwtProvider(
  @param:Value("\${coinbase.key.name:coinbase}") private val name: String,
  @param:Value("\${coinbase.key.value}") private val privateKey: String
) {

  data class VerifiableJwtToken(val token: String, val ecPublicKey: ECPublicKey)

  fun sign(httpMethod: HttpMethod, uri: URI): String? {
    val header = buildMap {
      put("alg", "ES256")
      put("typ", "JWT")
      put("kid", name)
      put("nonce", Instant.now().epochSecond.toString())
    }

    val jwtUri = "${httpMethod.name()} ${uri.path}"

    val data = buildMap {
      put("iss", "cdp")
      put("nbf", Instant.now().epochSecond)
      put("exp", Instant.now().epochSecond + 120)
      put("sub", name)
      put("uri", jwtUri)
    }

    val parsedPrivateKey = PEMParser(StringReader(privateKey.replace("\\n", "\n"))).use { pemParser ->
      val converter = JcaPEMKeyConverter().setProvider("BC")
      when (val `object` = pemParser.readObject()) {
        is PrivateKey -> `object`
        is org.bouncycastle.openssl.PEMKeyPair -> converter.getPrivateKey(`object`.privateKeyInfo)
        else -> error("Unexpected private key format")
      }
    }

    // Convert to ECPrivateKey
    val keyFactory = KeyFactory.getInstance("EC")
    val keySpec = PKCS8EncodedKeySpec(parsedPrivateKey.encoded)
    val ecPrivateKey = keyFactory.generatePrivate(keySpec) as ECPrivateKey

    // create JWT
    val claimsSetBuilder = JWTClaimsSet.Builder()
    for (entry in data.entries) {
      claimsSetBuilder.claim(entry.key, entry.value)
    }
    val claimsSet = claimsSetBuilder.build()

    val jwsHeader = JWSHeader.Builder(JWSAlgorithm.ES256).customParams(header).build()
    val signedJWT = SignedJWT(jwsHeader, claimsSet)

    val signer: JWSSigner = ECDSASigner(ecPrivateKey)
    signedJWT.sign(signer)

    return signedJWT.serialize()
  }


  @PostConstruct
  fun init() {
    Security.addProvider(BouncyCastleProvider())
  }
}

