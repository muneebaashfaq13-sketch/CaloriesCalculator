import { Container } from "@material-ui/core";
import MealsListAdmin from "./MealsListAdmin";

const DatewiseListAdmin = ({ mealsMap }) => {

    return Object.entries(mealsMap).map((values) => (
        <div className='list-datewise'>
            <h1 className="welcome-header">{'Date: ' + values[0]}</h1>
            <div className='user-list-columns'>
                <Container><b>User Name</b></Container>
                <Container><b>Meal Name</b></Container>
                <Container className="container-alignment" ><b>Calories(kCal)</b></Container>
                <Container className="container-alignment" ><b>Price($)</b></Container>
                <Container className="container-alignment" ><b>Date</b></Container>
                <Container className="container-alignment" ><b>Action</b></Container>
            </div>
            <MealsListAdmin
                meals={values[1]}
            />
            <h1 className="welcome-header">__________</h1>
        </div>
    ));

};

export default DatewiseListAdmin;
