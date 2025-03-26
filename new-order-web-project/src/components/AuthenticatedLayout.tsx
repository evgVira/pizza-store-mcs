import {Outlet} from "react-router-dom"
import {Fragment} from "react";

export default function AuthenticatedLayout() {
    return (
        <Fragment>
            <Outlet/>
        </Fragment>
    )
}