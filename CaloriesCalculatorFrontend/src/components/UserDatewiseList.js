import { Container } from "@material-ui/core";
import MealsListUser from "./MealsListUser";

const UserDatewiseList = ({ meals, startDate, endDate, caloriesLimit, expenseLimit }) => {

    return meals.map((meal) => (
        <div className='list-datewise'>
            <div className='user-list-columns'>
                <Container><b>Name</b></Container>
                <Container><b>Calories</b></Container>
                <Container><b>Price($)</b></Container>
            </div>
            <MealsListUser
                meals={meals}
            />
        </div>
    ));

};

export default UserDatewiseList;
