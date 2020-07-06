package ru.orgunit.backend.dto;

public class ComboboxDto {

    private Integer limit;
    private Integer page;
    private Integer start;
    private String query;

    public ComboboxDto() {
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void generateStandartPaging(){
        this.setLimit(100);
        this.setPage(1);
        this.setStart(0);
    }
}
