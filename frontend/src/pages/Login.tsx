import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { LoginInput, useLogin } from "@/data/auth";
import { useFormik } from "formik"
import * as Yup from 'yup';
import { ReloadIcon } from "@radix-ui/react-icons"
import { useTranslation } from "react-i18next";
import { Label } from "@/components/ui/label";

export default function Login() {
    const { t } = useTranslation()
    const { login, isPending } = useLogin()

    const loginInput: LoginInput = {
        email: "",
        password: ""
    }

    const loginValidator = Yup.object({
        email: Yup.string().email(t('auth_screen.login.form.email.invalid')).required(t('auth_screen.login.form.email.required')),
        password: Yup.string().required(t('auth_screen.login.form.password.required'))
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
                <h1 className="font-semibold text-lg">{t('auth_screen.login.title')}</h1>
                <p className="text-sm">{t('auth_screen.login.description')}</p>
            </div>
            <div className="flex flex-col gap-2">
                <Label>{t('auth_screen.login.form.email.label')}</Label>
                <Input type="email" name="email" required onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.email}
                    placeholder={t('auth_screen.login.form.email.placeholder')}
                />
                {formik.errors.email && formik.touched.email && <span className="text-sm text-red-500">{formik.errors.email}</span>}
            </div>
            <div className="flex flex-col gap-2">
                <Label>{t('auth_screen.login.form.password.label')}</Label>
                <Input type="password" name="password" required onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.password}
                    placeholder={t('auth_screen.login.form.password.placeholder')}
                />
                {formik.errors.password && formik.touched.password && <span className="text-sm text-red-500">{formik.errors.password}</span>}
            </div>
            <Button disabled={isPending} type="submit">{t('auth_screen.login.form.button')} {isPending && <ReloadIcon className="ml-2 animate-spin" />}</Button>
            <p className="text-sm">{t('auth_screen.login.form.does_have_account')} <a href="/register" className="text-primary">{t('auth_screen.login.form.register_new')}</a></p>
        </form>
    )
}