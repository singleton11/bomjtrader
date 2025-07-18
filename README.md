# Bomj Trader - A strategy backtesting and execution platform.

![Bomj Trader](.idea/icon.svg)

"Bomj" word is a hint who you are going to become when you start trading. It's a reminder to stay focused, disciplined,
and patient in your alco trading journey.

## Getting Started

As Alpaca API is used for getting access to market data, you need to create an account on [Alpaca](https://alpaca.markets/)
and get your API key.

To start working with Bomj Trader, you need to create server/resources/application-local.yaml with the following
content:

```yaml
alpaca:
  key:
    id: your-api-key-id
    secret: your-api-key-secret
```
