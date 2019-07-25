import React from "react";

const CurrencyItemComponent = (
    {
        currency
    }
) => (
    <div>
        {currency.name} <input type='integer' defaultValue={currency.amount} />
    </div>
);

export default CurrencyItemComponent;
