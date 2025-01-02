import React from 'react';
import Breadcrumb from '../Breadcrumbs/Breadcrumb';
import TableBrand from '../Tables/TableBrand';

export default function ListBrand() {
	return (
		<>
			<Breadcrumb pageName='List Brands' />

			<div className='flex flex-col gap-10'>
				<TableBrand />
			</div>
		</>
	);
}
