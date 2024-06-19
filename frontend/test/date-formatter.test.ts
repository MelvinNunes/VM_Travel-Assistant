import { DateFormatter } from "../src/utils/date-formatter";

describe("Test date formatter", () => {
  it("Format a simple date with hours", async () => {
    expect(DateFormatter.formatDate("2024-06-19 21:00:00")).toBe(
      "19 de junho de 2024"
    );
  });
});
