import { Container } from "@material-ui/core";
import { useEffect, useState } from "react";
import axios from "../../api/axios";

const REPORTS = '/report';

const AdminReport = () => {

    const [reports, setReports] = useState([]);
    const [currentCount, setCurrentCount] = useState(0);
    const [prevCount, setPrevCount] = useState(0);
    const [currentAvg, setCurrentAvg] = useState(0);
    const [prevAvg, setPrevAvg] = useState(0);

    useEffect(() => {
        async function getReports() {
            try {

                if (reports.length === 0) {
                    const allReports = await axios.get(REPORTS,
                        {
                            headers: { 'Content-Type': 'application/json' }
                        }
                    );
                    setReports(allReports.data);
                }

                console.log(reports);
                setCurrentCount(reports[0].userCount);
                setCurrentAvg(reports[0].caloriesAverage);
                setPrevCount(reports[1].userCount);
                setPrevAvg(reports[1].caloriesAverage);

            } catch (err) {
                console.log(err);
            }
        }
        getReports();
    })

    return (
        <>
            <div className='user-list-row'>
                <Container className="container-alignment" >
                    <h1 className="report-header">Curret Week</h1>
                    <div className='user-list-columns'>
                        <Container className="container-alignment" ><b>Total Entries</b></Container>
                        <Container className="container-alignment" ><b>Average Calories</b></Container>
                    </div>
                    <div className='user-list-columns'>
                        <Container className="container-alignment" ><b>{currentCount}</b></Container>
                        <Container className="container-alignment" ><b>{currentAvg}</b></Container>
                    </div>
                </Container>
                <Container className="container-alignment" >
                    <h1 className="report-header">Pervious Week</h1>
                    <div className='user-list-columns'>
                        <Container className="container-alignment" ><b>Total Entries</b></Container>
                        <Container className="container-alignment" ><b>Average Calories</b></Container>
                    </div>
                    <div className='user-list-columns'>
                        <Container className="container-alignment" ><b>{prevCount}</b></Container>
                        <Container className="container-alignment" ><b>{prevAvg}</b></Container>
                    </div>
                </Container>
            </div>
        </>
    )
}
export default AdminReport;
