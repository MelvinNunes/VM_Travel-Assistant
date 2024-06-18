import Formatter from "../src/utils/formater";

describe("Test population formatter", () => {
  it("With thousand value 1000", async () => {
    expect(Formatter.formatPopulation(1000)).toBe("1k");
  });
});

describe("Test population formatter", () => {
  it("With thousand value 100000", async () => {
    expect(Formatter.formatPopulation(100000)).toBe("100k");
  });
});

describe("Test population formatter", () => {
  it("With thousand value 998400", async () => {
    expect(Formatter.formatPopulation(998400)).toBe("998k");
  });
});

describe("Test population formatter", () => {
  it("With thousand value 998600", async () => {
    expect(Formatter.formatPopulation(998600)).toBe("999k");
  });
});

describe("Test population formatter", () => {
  it("With million value 1998600", async () => {
    expect(Formatter.formatPopulation(1994300)).toBe("1.99M");
  });
});
