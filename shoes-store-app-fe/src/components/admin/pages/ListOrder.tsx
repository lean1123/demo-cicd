import React from 'react';
import Breadcrumb from '../Breadcrumbs/Breadcrumb';
import TableOrder from '../Tables/TableOrder';

function ListOrder() {
	return (
		<>
			<Breadcrumb pageName='List Orders' />

			<div className='flex flex-col gap-10'>
				<TableOrder />
			</div>
		</>
	);
}

export default ListOrder;
