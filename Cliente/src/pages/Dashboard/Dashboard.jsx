import React from "react";
import './Dashboard.css'
import Menu from "./components/Menu";

function Dashboard (){
   return(
        
    <div className="container-dashboard">
     <Menu/>
      <header className="bg-secundary">
        <section className="txt-white">
          <h2> Bienvenido </h2>
          <h3>¿Que quieres hacer el dia de hoy?</h3>
          <div className="selector-option">
    
          </div>
        </section>
        <section>
          <img
            src="/img/admin.png"
            alt="Imagen del Admin"
          />
        </section>
      </header>
      
     
    </div>
   );
}
export default Dashboard