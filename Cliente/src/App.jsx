import React from 'react';

import './App.css'
import HomePage from './pages/Home/HomePage';
import RegisterPage from './pages/Auth/RegisterPage';
import ErrorPage from './pages/Error/ErrorPage';
import Navbar from './components/Navbar/Navbar';
import Footer from './components/Footer/Footer';
import Property from './pages/Property/Property';
import { Route, Routes } from 'react-router-dom';
import { PropertyProvider } from './context/PropertyContext';
import { AuthProvider } from './context/AuthContext';
import { CountryProvider } from './context/CountryContext';
import PropertySale from './pages/PropertySale/PropertySale';
import { ServiceProvider } from './context/ServiceContext';
import FormProperty from './pages/FormProperty/FormProperty';
import EditProfile from './pages/EditProfile/EditProfile';
import YouReservation from './pages/Reservation/YouReservation';
import Account from './pages/Account/Account';
import LoginSecurity from './pages/LoginSecurity/LoginSecurity';
import ProtectedRoute from './ProtectedRoute';
import BlogPage from './pages/Blog/BlogPage';
import { BlogProvider } from './context/BlogContext';
import FormBlog from './pages/FormBlog/FormBlog';
import Dashboard from './pages/Dashboard/Dashboard';
import CrudProperty from './pages/Dashboard/pages/CrudProperty';
import Favorite from './pages/Favorite/Favorite';
import { FavoriteProvider } from './context/FavoriteContext';
import { ReserveProvider } from './context/ReserveContext';

function App() {
  return (
    <FavoriteProvider>
      <AuthProvider>
        <CountryProvider>
          <ServiceProvider>
            <BlogProvider>
              <PropertyProvider>
                <ReserveProvider>
                  <Routes>
                    <Route path="/auth" element={<RegisterPage />} />
                    <Route path="/auth/:option" element={<RegisterPage />} />
                    <Route path="/*" element={<MainApp />} />
                  </Routes>
                </ReserveProvider>
              </PropertyProvider>
            </BlogProvider>
          </ServiceProvider>
        </CountryProvider>
      </AuthProvider>
    </FavoriteProvider>
  )
}

function MainApp() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path='/' element={<HomePage />} />
        <Route path='/property/:id' element={<Property />} />
        <Route path='/comprar' element={<PropertySale />} />
        <Route path='/blog' element={<BlogPage />} />
        <Route element={<ProtectedRoute />}>
          <Route path='/form-blog' element={<FormBlog />} />
          <Route path='/form-property' element={<FormProperty />} />
          <Route path='/you-reservation' element={<YouReservation />} />
          <Route path='/edit-profile' element={<EditProfile />} />
          <Route path='/account' element={<Account />} />
          <Route path='/login-security' element={<LoginSecurity />} />

        </Route>
        <Route path='*' element={<ErrorPage />} />
        <Route path='/dashboard' element={<Dashboard />} />
        <Route path='/crud-property' element={<CrudProperty />} />
        <Route path='/favorite' element={<Favorite />} />
      </Routes>
      <Footer />
    </>
  )
}

export default App;

