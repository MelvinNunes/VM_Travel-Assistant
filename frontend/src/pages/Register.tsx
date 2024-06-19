import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { RegisterInput, useRegister } from "@/data/auth";
import { ReloadIcon } from "@radix-ui/react-icons";
import { useFormik } from "formik";
import { useTranslation } from "react-i18next";
import * as Yup from 'yup';

export default function RegisterScreen() {
    const { t } = useTranslation()
    const { register, isPending } = useRegister()

    const registerInput: RegisterInput = {
        name: "",
        email: "",
        password: ""
    }

    const registerValidator = Yup.object({
        name: Yup.string().required(t('auth_screen.register.form.name.required')),
        email: Yup.string().email(t('auth_screen.register.form.email.invalid')).required(t('auth_screen.register.form.email.required')),
        password: Yup.string().min(4, t('auth_screen.register.form.password.min')).required(t('auth_screen.register.form.password.required'))
    })

    const formik = useFormik({
        initialValues: registerInput,
        validationSchema: registerValidator,
        onSubmit: (values, { setSubmitting }) => {
            register(values)
            setSubmitting(false)
        }
    })

    return (
        <form onSubmit={formik.handleSubmit} className="flex flex-col gap-4 px-10 md:px-20 lg:px-96 my-10">
            <div className="flex flex-col mb-5">
                <h1 className="font-semibold text-lg dark:text-slate-400">{t('auth_screen.register.title')}</h1>
                <p className="text-sm dark:text-slate-500">{t('auth_screen.register.description')}</p>
            </div>
            <div className="flex flex-col gap-2">
                <Label className="dark:text-slate-400">{t('auth_screen.register.form.name.label')}</Label>
                <Input type="text" name="name" required onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.name}
                    placeholder={t('auth_screen.register.form.name.placeholder')}
                />
                {formik.errors.name && formik.touched.name && <span className="text-sm text-red-500">{formik.errors.name}</span>}
            </div>
            <div className="flex flex-col gap-2">
                <Label className="dark:text-slate-400">{t('auth_screen.register.form.email.label')}</Label>
                <Input type="email" name="email" required onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.email}
                    placeholder={t('auth_screen.register.form.email.placeholder')}
                />
                {formik.errors.email && formik.touched.email && <span className="text-sm text-red-500">{formik.errors.email}</span>}
            </div>
            <div className="flex flex-col gap-2">
                <Label className="dark:text-slate-400">{t('auth_screen.register.form.password.label')}</Label>
                <Input type="password" name="password" required onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values.password}
                    placeholder={t('auth_screen.register.form.password.placeholder')}
                />
                {formik.errors.password && formik.touched.password && <span className="text-sm text-red-500">{formik.errors.password}</span>}
            </div>
            <Button disabled={isPending} type="submit">{t('auth_screen.register.form.button')} {isPending && <ReloadIcon className="ml-2 animate-spin" />}</Button>
            <p className="text-sm dark:text-slate-500">{t('auth_screen.register.form.already_have')} <a href="/login" className="text-primary">{t('auth_screen.register.form.login_to')}</a> </p>
        </form>
    )
}