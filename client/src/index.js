import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import * as Redux from "redux";
import {combineReducers} from "redux";
import {Provider} from "react-redux";
import {composeWithDevTools} from "redux-devtools-extension";
import currencyReducer from "./app/home/converter/reducers";


const rootReducer = combineReducers({
    currenciesState: currencyReducer
});

const store = Redux.createStore(rootReducer, composeWithDevTools());

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);



