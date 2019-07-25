import React from "react";

const ResultComponent = (
    {
        result,
        resultCurrency
    }
) => {
    return (
        <div>
            Result: {result} {resultCurrency}
        </div>
    )
};

export default ResultComponent;
