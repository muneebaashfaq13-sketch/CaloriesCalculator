import { Container } from "@material-ui/core";

const MealsListUser = ({ meals }) => {

    return meals.map((meal) => (
        <div className='user-list-row'>
            <Container>{meal.mealName}</Container>
            <Container className="container-alignment" >{meal.calories}</Container>
            <Container className="container-alignment" >{meal.price}</Container>
        </div>
    ));

};

export default MealsListUser;
