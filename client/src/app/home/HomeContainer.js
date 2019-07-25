import HomeComponent from "./HomeComponent";
import {connect} from "react-redux";
import {addCurrency, switchCurrency} from "./converter/actions";

const mapStateToProps = (state) => {
    const {currencies, resultCurrency, result} = state.currenciesState;
    return {
        currencies,
        resultCurrency,
        result
    }
};

const mapDispatchToProps = (dispatch) => (
    {
        switchCurrency: (event) => dispatch(switchCurrency(event)),
        addCurrency: (currency) => dispatch(addCurrency(currency))
    }
);

const HomeContainer = connect(
    mapStateToProps,
    mapDispatchToProps
)(HomeComponent);

export default HomeContainer;
