import { useContext, useState } from "react";
import { createContext } from "react";
import { getBlogs, postBlog } from "../service/blog";

export const BlogContext = createContext();

export const useBlog = () => {
    const context = useContext(BlogContext);
    if (!context) {
        throw new Error("El useBlog debe estar dentro del contexto");
    }
    return context;
};
export const BlogProvider = ({ children }) =>{
    const[blogAll, setBlogAll] = useState([]);

    const _getBlog = async () =>{
        try{
            const blog = await getBlogs();
            setBlogAll(blog.data);
        } catch (error){
            console.log(error);
        }
    };

    const _postBlog = async (blog) =>{
        try{
            const res = await postBlog(blog);
            console.log(res.data);
        } catch(error){
            console.log(error);
        }
    };

    return(
        <BlogContext.Provider 
        value={{
            _getBlog,
            _postBlog,
            blogAll,
        }}>
            {children}
        </BlogContext.Provider>
    )
}