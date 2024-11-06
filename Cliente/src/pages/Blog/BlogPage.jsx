import React from "react";
import "./BlogPage.css";
import CardBlog from "./components/CardBlog";
import { Link } from "react-router-dom";

const BlogPage = () => {
  return (
    <div className="container-major">
      <h2>Blog de viajes</h2>
      <div className="form-exper">
        <Link to={"/form-blog"}>
          <button className="txt-white cursor-pointer">
            añadir una experiencia
          </button>
        </Link>
      </div>

      <CardBlog />
    </div>
  );
};

export default BlogPage;
