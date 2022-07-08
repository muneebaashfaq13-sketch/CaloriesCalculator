import { Link } from "react-router-dom"

const User = () => {
    return (
        <section>
            <h1>User Page</h1>
            <br />
            <p>You must have been assigned an Editor role.</p>
            <div className="flexGrow">
            <button>Refresh</button>
            </div>
        </section>
    )
}

export default User
