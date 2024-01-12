// Components
import HeaderComponent from "../../components/common/HeaderComponent";
import TripComponent from "../../components/attraction/AttractionComponent";

// Style
import tripStyle from "../../resources/css/attraction/Attraction.module.css";

function AttractionContainer() {

  return (
    <>
      <HeaderComponent />
      <div className={tripStyle.container}>
        <TripComponent tripStyle={tripStyle} />
      </div>
    </>
  );
}

export default AttractionContainer;
