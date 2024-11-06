import React, { useEffect } from 'react';
import "./CardBlog.css";
import { useBlog } from '../../../context/BlogContext';

const CardBlog = () => {
    const { blogAll, _getBlog} = useBlog();

    useEffect(() => {
        _getBlog();
    },[]);

    return(
        <div className="card-blog txt-black">
            {blogAll.map((blogs) => (
                <div key={blogs.id} className="blog cursor-pointer">
                <img key={blogs.id} src={blogs.images[0]} alt={blogs.images} />
                <div className="container-details">
                    <h3>{blogs.title}</h3>
                    <div className="info">
                        <h4>2021/03/23</h4>
                        <p>{blogs.description}</p>
                    </div>
                </div>
            </div>
            ))}
        </div>
    )
}

export default CardBlog