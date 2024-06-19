import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { LoginInput, useLogin } from "@/data/auth";
import { useFormik } from "formik"
import * as Yup from 'yup';
import { ReloadIcon } from "@radix-ui/react-icons"

export default function Login() {
    const { login, isPending } = useLogin()

    const loginInput: LoginInput = {
        email: "",
        password: ""
    }

    const loginValidator = Yup.object({
        email: Yup.string().email("Invalid email address").required("Email is required"),
        password: Yup.string().required("Password is required")
    })

    const formik = useFormik({
        initialValues: loginInput,
        validationSchema: loginValidator,
        onSubmit: (values, { setSubmitting }) => {
            login(values)
            setSubmitting(false)
        }
    })

    return (
        <form onSubmit={formik.handleSubmit} className="flex flex-col gap-4 px-10 md:px-20 lg:px-96 my-10">
            <div className="flex flex-col mb-5">
                <h1 className="font-semibold text-lg">Login to your account</h1>
                <p className="text-sm">Login and plan your next trip!</p>
            </div>
            <Input type="email" name="email" required onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.email} placeholder="Enter email address" />
            <Input type="password" name="password" required onChange={formik.handleChange}
                onBlur={formik.handleBlur}
                value={formik.values.password} placeholder="Enter a password" />
            <Button disabled={isPending} type="submit">Login {isPending && <ReloadIcon className="ml-2 animate-spin" />}</Button>
            <p className="text-sm">New to Destinos? Create an account</p>
        </form>
    )
}