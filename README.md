[![Build Status](https://travis-ci.com/rgosik/NBP_Data_Parser.svg?branch=master)](https://travis-ci.com/rgosik/NBP_Data_Parser)
# NBP Data Parser

Program receives three command-line arguments.
First one is the "currency code".
The two other are, respectively, "starting date" and "ending date" of the data that program will download to perform its calculations. 

The output consists of mean, buy or sell rate and buy or sell standard deviation of the given currency, within the given time period.
These calculations can be performed on any currency provided by NBP. The earliest available data provided by NBP is from 2002 

This program doesn't use NBP Web API 

<https://www.nbp.pl/home.aspx?f=/kursy/instrukcja_pobierania_kursow_walut.html>
