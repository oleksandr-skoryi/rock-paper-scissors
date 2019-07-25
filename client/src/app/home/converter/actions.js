import {ADD_CURRENCY, SWITCH_RESULT_CURRENCY} from "./types";

export const switchCurrency = (event) => {
    return {
        type: SWITCH_RESULT_CURRENCY,
        value: event.target.value
    }
};

export const calculateMoney = (event) => {

};

export const addCurrency = (currency) => {
    console.log('addCurrency invoked');
    return {
        type: ADD_CURRENCY,
        currency
    }
};
