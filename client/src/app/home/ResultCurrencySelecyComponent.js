import React from "react";

const ResultCurrencySelectComponent = (
    {
        switchCurrency,
        selectValue
    }
) => (
    <div>
        <select name="currency_select" id="currency_select" onChange={switchCurrency} value={selectValue}>
            <option>USD</option>
            <option>EUR</option>
            <option>UAH</option>
        </select>
    </div>
);

export default ResultCurrencySelectComponent;
