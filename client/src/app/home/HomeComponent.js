import React from "react";
import CurrencyListComponent from "./CurrencyListComponent";
import ControlComponent from "./ControlComponent";
import ResultCurrencySelectComponent from "./ResultCurrencySelecyComponent";
import ResultComponent from "./ResultComponent";
import AddCurrencyComponent from "./AddCurrencyComponent";

const HomeComponent = (
    {
        currencies,
        switchCurrency,
        resultCurrency,
        result,
        addCurrency
    }
) => (
    <div>
        <CurrencyListComponent currencies={currencies}/>
        <AddCurrencyComponent addCurrency={addCurrency}/>
        <ControlComponent/>
        <ResultCurrencySelectComponent switchCurrency={switchCurrency} selectValue={resultCurrency}/>
        <ResultComponent result={result} resultCurrency={resultCurrency}/>
    </div>
);

export default HomeComponent;
