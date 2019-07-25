import {ADD_CURRENCY, SWITCH_RESULT_CURRENCY} from "./types";

const INITIAL_STATE = {
    currencies: [
        {
            name: 'USD',
            amount: '1000'
        },
        {
            name: 'EUR',
            amount: '2000'
        },
    ],
    resultCurrency: 'USD',
    result: 0
};

const currencyReducer = (state = INITIAL_STATE, action) => {
    switch (action.type) {
        case SWITCH_RESULT_CURRENCY: {
            return {
                ...state,
                resultCurrency: action.value
            }
        }
        case ADD_CURRENCY: {
            return {
                ...state,
                currencies: [...state.currencies, action.currency]
            }
        }
    }
    return state;
};

export default currencyReducer;
