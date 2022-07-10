import { Container } from "@material-ui/core";

const customStyle = {
    centerAlign: {
        textAlign: 'center'
    },
};

const MealsListAdmin = ({ allMeal }) => {

    console.log(allMeal);

    return allMeal.map((meal) => (
        <div className='user-list-row'>
            <Container>{meal.userName}</Container>
            <Container>{meal.mealName}</Container>
            <Container style={customStyle.centerAlign} >{meal.calories}</Container>
            <Container style={customStyle.centerAlign} >{meal.price}</Container>
            <Container style={customStyle.centerAlign} >{meal.date}</Container>
            <Container style={customStyle.centerAlign} >
                <button className="edit-button" >Edit</button>
                <button className="delete-button" >Delete</button>
            </Container>
        </div>
    ));

};

export default MealsListAdmin;
