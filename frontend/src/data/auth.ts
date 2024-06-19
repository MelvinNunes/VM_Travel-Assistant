import { useMutation } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";
import { HttpClient } from "./client/http-client";
import { API_ENDPOINTS } from "./client/endpoints";
import { setAuthToken } from "./client/token.utils";
import { toast } from "@/components/ui/use-toast";
import { useAtom } from "jotai";
import { isUserAuthenticated } from "@/atoms/auth";

type LoginResponse = {
  message: string;
  token: string;
};

export type LoginInput = {
  email: string;
  password: string;
};

export function useLogin() {
  const navigate = useNavigate();
  const [, setIsUserAuth] = useAtom(isUserAuthenticated);

  const { isPending, isError, mutate, isSuccess } = useMutation({
    mutationFn: (data: LoginInput) => {
      return HttpClient.post<LoginResponse>(API_ENDPOINTS.LOGIN, data);
    },
    onSuccess: (data) => {
      setAuthToken(data?.token);
      setIsUserAuth(true);
      navigate("/");
    },
    onError: () => {
      toast({
        title: "Ocorreu um erro",
        description: "Por favor verifique as suas credenciais!",
        variant: "destructive",
      });
    },
  });

  return {
    isPending,
    isSuccess,
    isError,
    login: mutate,
  };
}

type RegisterResponse = {
  message: string;
  token: string;
};

type RegisterInput = {
  name: string;
  email: string;
  password: string;
};

export function useRegister() {
  const navigate = useNavigate();

  const { isPending, isError, mutate, isSuccess } = useMutation({
    mutationFn: (data: RegisterInput) => {
      return HttpClient.post<RegisterResponse>(API_ENDPOINTS.REGISTER, data);
    },
    onSuccess: () => {
      //   setAuthToken(data?.token);

      //   const decoded: DecodedToken = jwtDecode(data?.data.token);
      //   setIsUserOnline(decoded.sub);

      navigate("/");
    },
    onError: () => {
      //   toast({
      //     title: "Ocorreu um erro",
      //     description: "Por favor verifique as suas credenciais!",
      //     variant: "destructive",
      //   });
    },
  });

  return {
    isPending,
    isSuccess,
    isError,
    Register: mutate,
  };
}
