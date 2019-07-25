import React from "react";
import CurrencyItemComponent from "./CurrencyItemComponent";


const CurrencyListComponent = (
    {
        currencies
    }
) => (
    <div>
        {
            currencies.map(
                (currency, index) => (
                    <CurrencyItemComponent key={index} currency={currency}/>
                )
            )
        }
    </div>
);

export default CurrencyListComponent;
