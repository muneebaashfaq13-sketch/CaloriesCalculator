import { Container } from "@material-ui/core";

const MealsListUser = ({ meals}) => {

    return meals.map((meal) => (
        <div className='user-list-row'>
            <Container>{meal.mealName}</Container>
            <Container>{meal.calories}</Container>
            <Container>{meal.price}</Container>
        </div>
    ));

};

export default MealsListUser;
