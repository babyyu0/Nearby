// Components
import HeaderComponent from "../../components/common/HeaderComponent";
import AttractionComponent from "../../components/attraction/AttractionComponent";

// Style
import attractionStyle from "../../resources/css/attraction/Attraction.module.css";

function AttractionContainer() {

  return (
    <>
      <HeaderComponent />
      <div className={attractionStyle.attractionContainer}>
        <AttractionComponent attractionStyle={attractionStyle} />
      </div>
    </>
  );
}

export default AttractionContainer;
