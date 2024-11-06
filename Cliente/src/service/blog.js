import axios from "./axios"

export const getBlogs = async () => axios.get("/blog/getblog");

export const postBlog = async (blog) => axios.post("/blog/register", blog);