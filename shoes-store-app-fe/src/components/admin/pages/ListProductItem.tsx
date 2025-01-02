import React from 'react';
import Breadcrumb from '../Breadcrumbs/Breadcrumb';
import TableProductItem from '../Tables/TableProductItem';

function ListProductItem() {
	return (
		<>
			<Breadcrumb pageName='List Product Items' />

			<div className='flex flex-col gap-10'>
				<TableProductItem />
			</div>
		</>
	);
}

export default ListProductItem;
