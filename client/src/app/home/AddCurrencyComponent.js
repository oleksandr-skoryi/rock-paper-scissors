import React, {useState} from "react";

const AddCurrencyComponent = (
    {
        addCurrency,
    }
) => {
    const [resultState, setNewState] = useState({});

    const setName = (e) => {
        setNewState(
            {
                ...resultState, name: e.target.value
            }
        );
        console.log(resultState);
    };

    const setValue = (e) => {
        setNewState(
            {
                ...resultState, amount: e.target.value
            }
        );
        console.log(resultState);
    };

    return (
        <div>
            <select name="currency_select_add" id="currency_select_add" onChange={setName}>
                <option>USD</option>
                <option>EUR</option>
                <option>UAH</option>
            </select>
            <input type='text' id='currency_input_add' onChange={setValue}/>
            <button onClick={() => addCurrency(resultState)}>Add Currency</button>
        </div>
    );
};

export default AddCurrencyComponent;
