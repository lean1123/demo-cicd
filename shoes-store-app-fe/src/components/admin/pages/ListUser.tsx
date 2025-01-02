import React from 'react';
import Breadcrumb from '../Breadcrumbs/Breadcrumb';
import TableUser from '../Tables/TableUser';

function ListUser() {
	return (
		<>
			<Breadcrumb pageName='List Users' />

			<div className='flex flex-col gap-10'>
				<TableUser />
			</div>
		</>
	);
}

export default ListUser;
