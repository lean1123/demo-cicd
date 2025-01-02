import React, { useEffect, useState } from 'react';
import { Route, Routes, useLocation } from 'react-router';
import AdminLayout from './AdminLayout';
import Dashboard from './pages/Dashboard';
import ListProduct from './pages/ListProduct';
import CreateProduct from './pages/CreateProduct';
import AddProductItem from './pages/AddProductItem';
import ListBrand from './pages/ListBrand';
import CreateBrand from './pages/CreateBrand';
import AddCollection from './pages/AddCollection';
import ListCategory from './pages/ListCategory';
import CreateCategory from './pages/CreateCategory';
import ListOrder from './pages/ListOrder';
import OrderDetail from './pages/OrderDetail';
import ListCollection from './pages/ListCollection';
import ListProductItem from './pages/ListProductItem';
import EditProduct from './pages/EditProduct';
import EditProductItem from './pages/EditProductItem';
import ListUser from './pages/ListUser';
import UserDetail from './pages/UserDetail';
import EditUser from './pages/EditUser';
import EditBrand from './pages/EditBrand';
import EditCollection from './pages/EditCollection';
import EditCategory from './pages/EditCategory';

function AdminPage() {
	const [loading, setLoading] = useState(true);
	const { pathname } = useLocation();

	useEffect(() => {
		window.scrollTo(0, 0);
	}, [pathname]);

	useEffect(() => {
		setTimeout(() => {
			setLoading(false);
		}, 1000);
	}, []);
	return loading ? (
		'Loading...'
	) : (
		<AdminLayout>
			<Routes>
				<Route index element={<Dashboard />} />
				<Route path='/products/list' element={<ListProduct />} />
				<Route path='/products/add' element={<CreateProduct />} />
				<Route path='/products/edit/:id' element={<EditProduct />} />
				<Route path='/products/:id/add-item' element={<AddProductItem />} />
				<Route path='/products/:id/list-item' element={<ListProductItem />} />
				<Route
					path='/products/:id/list-item/:itemId/edit'
					element={<EditProductItem />}
				/>
				<Route path='/brands/list' element={<ListBrand />} />
				<Route path='/brands/add' element={<CreateBrand />} />
				<Route path='/brands/:id/edit' element={<EditBrand />} />
				<Route path='/brands/add-collection/:id' element={<AddCollection />} />
				<Route path='/brands/view-collection/:id' element={<ListCollection />} />
				<Route
					path='/brands/view-collection/:id/:collectionId/edit'
					element={<EditCollection />}
				/>
				<Route path='/categories/list' element={<ListCategory />} />
				<Route path='/categories/add' element={<CreateCategory />} />
				<Route path='/categories/:id/edit' element={<EditCategory />} />
				<Route path='/users/list' element={<ListUser />} />
				<Route path='/users/:id' element={<UserDetail />} />
				<Route path='/users/:id/edit' element={<EditUser />} />
				<Route path='/orders' element={<ListOrder />} />
				<Route path='/orders/:id' element={<OrderDetail isEdit={false} />} />
				<Route path='/orders/:id/edit' element={<OrderDetail isEdit={true} />} />
			</Routes>
		</AdminLayout>
	);
}

export default AdminPage;
