import { format } from "date-fns";
import { ptBR } from "date-fns/locale";

export class DateFormatter {
  static formatDate(dateString: string): string {
    const date = new Date(dateString);
    const formattedDate = format(date, "d 'de' MMMM 'de' yyyy 'às' H:mm", {
      locale: ptBR,
    });
    return formattedDate;
  }
}
