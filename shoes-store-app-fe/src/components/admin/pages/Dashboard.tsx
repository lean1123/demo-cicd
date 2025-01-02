import React from 'react';
import CardDataStats from '../../common/admin/CardDataStats';
import ChartOne from '../Charts/ChartOne';
import ChartTwo from '../Charts/ChartTwo';
import ChartThree from '../Charts/ChartThree';
import TableOne from '../Tables/TableOne';
import {
	GroupOutlined,
	ShoppingBagOutlined,
	ShoppingCartOutlined,
	VisibilityOutlined,
} from '@mui/icons-material';

const Dashboard: React.FC = () => {
	return (
		<>
			<div className='grid grid-cols-1 gap-4 md:grid-cols-2 md:gap-6 xl:grid-cols-4 2xl:gap-7.5'>
				<CardDataStats title='Total views' total='$3.456K' rate='0.43%' levelUp>
					<div className='w-14 h-14 rounded-full flex justify-center items-center bg-slate-200'>
						<VisibilityOutlined className='text-primary' />
					</div>
				</CardDataStats>
				<CardDataStats title='Total Profit' total='$45,2K' rate='4.35%' levelUp>
					<div className='w-12 h-12 rounded-full flex justify-center items-center bg-slate-200'>
						<ShoppingCartOutlined className='text-primary' />
					</div>
				</CardDataStats>
				<CardDataStats title='Total Product' total='2.450' rate='2.59%' levelUp>
					<div className='w-12 h-12 rounded-full flex justify-center items-center bg-slate-200'>
						<ShoppingBagOutlined className='text-primary' />
					</div>
				</CardDataStats>
				<CardDataStats title='Total Users' total='3.456' rate='0.95%' levelDown>
					<div className='w-12 h-12 rounded-full flex justify-center items-center bg-slate-200'>
						<GroupOutlined className='text-primary' />
					</div>
				</CardDataStats>
			</div>

			<div className='mt-4 grid grid-cols-12 gap-4 md:mt-6 md:gap-6'>
				<ChartOne />
				<ChartTwo />
				<ChartThree />
				{/* <MapOne /> */}
				<div className='col-span-12 xl:col-span-6'>
					<TableOne />
				</div>
				{/* <ChatCard /> */}
			</div>
		</>
	);
};

export default Dashboard;
