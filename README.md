# CryptoWallet
This is a small project that I made in university.

It's a SpringBoot back end with an Angular front end.

The idea behind this app was to be able explore the evolution of Cryptocurrencies and to 'invest' using fictive money.

It has a background process that is waken up every half an hour and downloads the new prices for the top 200 most transacted coins, parses them and saves them in an sql database.

The data is served to the front end through a REST API.
