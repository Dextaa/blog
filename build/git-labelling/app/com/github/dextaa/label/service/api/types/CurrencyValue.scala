package com.github.dextaa.label.service.api.types

/**
 * Currency code and value encapsulation.
 * @param currencyCode the ISO currency code e.g. USD, GBP.
 * @param value a value expressed in #currencyCode.
 */
case class CurrencyValue(currencyCode: CurrencyCode, value: BigDecimal)
