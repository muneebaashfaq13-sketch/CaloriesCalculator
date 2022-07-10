import { Container } from "@material-ui/core";

const customStyle = {
    centerAlign: {
        textAlign: 'center'
    },
};

const MealsListUser = ({meals}) => {

    return meals.map((meal) => (
        <div className='user-list-row'>
            <Container>{meal.mealName}</Container>
            <Container style={customStyle.centerAlign} >{meal.calories}</Container>
            <Container style={customStyle.centerAlign} >{meal.price}</Container>
        </div>
    ));

};

export default MealsListUser;
