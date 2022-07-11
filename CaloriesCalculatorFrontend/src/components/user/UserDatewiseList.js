import { Container } from "@material-ui/core";
import MealsListUser from "./MealsListUser";

function total(meals, attrib) {
    var sum = 0;

    meals.forEach(meal => {
        if (attrib === 'calories') {
            sum += meal.calories;
        } else if (attrib === 'price') {
            sum += meal.price;
        }
    });

    return sum;
}

function inLimit(meals, attrib, limit) {

    if (total(meals, attrib) <= limit)
        return true;
    return false;
}

const UserDatewiseList = ({ mealsMap, startDate, endDate, caloriesLimit, expenseLimit }) => {

    return Object.entries(mealsMap).map((values) => (
        <div className='list-datewise'>
            <h1 className="welcome-header">{'Date: ' + values[0]}</h1>
            <div className={inLimit(values[1], 'calories', caloriesLimit) ? 'in-limit' : 'out-limit'}>{'Calories: ' + total(values[1], 'calories') + '/' + caloriesLimit}</div>
            <div className={inLimit(values[1], 'price', expenseLimit) ? 'in-limit' : 'out-limit'}>{'Expense: ' + total(values[1], 'price') + '/' + expenseLimit}</div>
            <div className='user-list-columns'>
                <Container><b>Name</b></Container>
                <Container className="container-alignment" ><b>Calories(kCal)</b></Container>
                <Container className="container-alignment" ><b>Price($)</b></Container>
            </div>
            <MealsListUser
                meals={values[1]}
            />
            <h1 className="welcome-header">__________</h1>
        </div>
    ));

};

export default UserDatewiseList;
