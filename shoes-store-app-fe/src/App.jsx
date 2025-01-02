import { Route, Routes, useLocation } from 'react-router';
import './App.css';

import { useSelector } from 'react-redux';
import AdminPage from './components/admin';
import LoginForm from './components/auth/LoginForm';
import SignUpForm from './components/auth/SignUpForm';
import Cart from './components/cart/Cart';
import Pay from './components/cart/pay/Pay';
import Footer from './components/footer';
import Header from './components/header';
import HomePage from './components/home';
import DescriptionInfo from './components/product/detail/DescriptionInfo';
import ProductDetail from './components/product/detail/ProductDetail';
import ReturnPolicy from './components/product/detail/ReturnPolicy';
import WarrantyPolicy from './components/product/detail/WarrantyPolicy';
import ListAllProducts from './components/product/ListAllProducts';
import ListNewProducts from './components/product/new/ListNewProducts';
import ListRecentProducts from './components/product/recent/ListRecentProducts';
import ListReview from './components/product/review/ListReview';
import ListTopSaleProducts from './components/product/topSale/ListTopSaleProducts';
import Address from './components/profile/Address';
import Profile from './components/profile/Profile';
import UpdateProfile from './components/profile/UpdateProfile';
import SuccessfullyPayment from './components/cart/pay/SuccessfullyPayment';
import OrderDetails from './components/order/OrderDetails';

function App() {
	const location = useLocation();

	const { user } = useSelector((state) => state.persistedReducer.userInfo);
	const role = user?.role === 'CUSTOMER' ? 'customer' : 'admin';

	return (
		<div className='App'>
			{!location.pathname.includes('/admin') && <Header />}

			<Routes>
				<Route path='/login' element={<LoginForm />} />
				<Route path='/signup' element={<SignUpForm />} />
				<Route path='/profile' element={<Profile />} />
				<Route path='/cart' element={<Cart />} />
				<Route path='/updateProfile' element={<UpdateProfile />} />
				<Route path='/address' element={<Address />} />
				<Route path='/'>
					<Route index element={<HomePage />} />
					<Route path='listNewProducts' element={<ListNewProducts />} />
					<Route path='listRecentProducts' element={<ListRecentProducts />} />
					<Route path='listTopSaleProducts' element={<ListTopSaleProducts />} />
					<Route path='products'>
						<Route index element={<ListAllProducts />} />
						<Route path=':id' element={<ProductDetail />}>
							<Route path='description' element={<DescriptionInfo />} />
							<Route path='reviews' element={<ListReview />} />
							<Route path='warrantyPolicy' element={<WarrantyPolicy />} />
							<Route path='returnPolicy' element={<ReturnPolicy />} />
						</Route>
					</Route>
					<Route path='order/:orderId' element={<OrderDetails />} />
					{(role === 'admin' || role === 'customer') && (
						<Route path='pay' element={<Pay />} />
					)}
					<Route path='/orderSuccess' element={<SuccessfullyPayment />} />
					<Route path='/orderFail' element={<SuccessfullyPayment />} />
					<Route path='post' element={<h1>Post</h1>} />
					<Route path='*' element={<h1>404 Not Found</h1>} />
				</Route>

				{role === 'admin' && <Route path='admin/*' element={<AdminPage />} />}
			</Routes>
			{location.pathname !== '/cart' &&
				location.pathname !== '/cart/pay' &&
				!location.pathname.includes('/admin') && <Footer />}
		</div>
	);
}

export default App;
