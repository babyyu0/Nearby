// Components
import HeaderComponent from "../../components/common/HeaderComponent";
import TripComponent from "../../components/trip/TripComponent";

// Style
import tripStyle from "../../resources/css/trip/Trip.module.css";

function TripContainer() {

  return (
    <>
      <HeaderComponent />
      <div className={tripStyle.container}>
        <TripComponent />
      </div>
    </>
  );
}

export default TripContainer;
