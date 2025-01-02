import { useForm } from 'react-hook-form';
import { useDispatch } from 'react-redux';
import { register as registerAction } from '../../hooks/auth/authSlice';
import { unwrapResult } from '@reduxjs/toolkit';
import { enqueueSnackbar } from 'notistack';
import { fetchUser } from '../../hooks/user/userSlice';

function SignUpForm() {
	const dispatch = useDispatch();

	const {
		register,
		handleSubmit,
		formState: { errors },
	} = useForm({
		defaultValues: {
			firstName: '',
			lastName: '',
			email: '',
			password: '',
		},
	});

	const onSubmit = async (data) => {
		console.log('Form Data:', data);
		// Xử lý logic gửi form ở đây

		try {
			const result = await dispatch(registerAction(data));
			const dataResult = unwrapResult(result);
			if (dataResult?.userId) {
				dispatch(fetchUser(dataResult.userId));
				enqueueSnackbar('Đăng ký thành công', { variant: 'success' });
				return;
			}

			enqueueSnackbar('Đăng ký thất bại', { variant: 'error' });
		} catch (error) {
			console.error('Failed to register:', error);
			enqueueSnackbar('Đăng ký thất bại', { variant: 'error' });
		}
	};

	return (
		<>
			<div className='flex justify-center pt-5'>
				<div className='boder-createAccount pt-3'>
					<div className='flex justify-center'>
						<h1 className='font-bold text-xl justify-center'>Thêm Tài Khoản</h1>
					</div>
					<form onSubmit={handleSubmit(onSubmit)}>
						<div className='flex items-center pt-3'>
							<label htmlFor='firstName' className='ml-5 font-bold'>
								Tên đầu:
							</label>
							<div className='ml-8 mb-4 search-container w-3/5'>
								<input
									className='w-full boder no-border py-1 input-field'
									type='text'
									id='firstName'
									{...register('firstName', { required: 'Tên đầu là bắt buộc' })}
								/>
								{errors.firstName && (
									<p className='text-red-500 text-sm'>{errors.firstName.message}</p>
								)}
							</div>
						</div>
						<div className='flex items-center pt-3'>
							<label htmlFor='lastName' className='ml-5 font-bold'>
								Tên cuối:
							</label>
							<div className='ml-8 mb-4 search-container w-3/5'>
								<input
									className='w-full boder no-border py-1 input-field'
									type='text'
									id='lastName'
									{...register('lastName', { required: 'Tên cuối là bắt buộc' })}
								/>
								{errors.lastName && (
									<p className='text-red-500 text-sm'>{errors.lastName.message}</p>
								)}
							</div>
						</div>
						<div className='flex items-center pt-3'>
							<label htmlFor='email' className='ml-5 font-bold'>
								Email:
							</label>
							<div className='ml-8 mb-4 search-container w-3/5'>
								<input
									className='w-full boder no-border py-1 input-field'
									type='email'
									id='email'
									{...register('email', {
										required: 'Email là bắt buộc',
										pattern: {
											value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
											message: 'Email không hợp lệ',
										},
									})}
								/>
								{errors.email && (
									<p className='text-red-500 text-sm'>{errors.email.message}</p>
								)}
							</div>
						</div>
						<div className='flex items-center pt-3'>
							<label htmlFor='password' className='ml-5 font-bold'>
								Mật khẩu:
							</label>
							<div className='ml-8 mb-4 search-container w-3/5'>
								<input
									className='w-full boder no-border py-1 input-field'
									type='password'
									id='password'
									{...register('password', {
										required: 'Mật khẩu là bắt buộc',
										minLength: {
											value: 6,
											message: 'Mật khẩu phải có ít nhất 6 ký tự',
										},
									})}
								/>
								{errors.password && (
									<p className='text-red-500 text-sm'>{errors.password.message}</p>
								)}
							</div>
						</div>
						<div className='flex justify-center pt-3'>
							<button
								type='submit'
								className='px-4 py-2 bg-blue-500 text-white font-bold rounded'
							>
								Tạo Tài Khoản
							</button>
						</div>
					</form>
				</div>
			</div>
		</>
	);
}

export default SignUpForm;
