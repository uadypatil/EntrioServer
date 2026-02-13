# Entrio Server – Database Documentation

This document provides a complete overview of the database schema used in the **Entrio Server** application.  
It includes table structures, columns, data types, enums, and relationships.

executor: call this command to execute the application
D:\Uday\eclipse\STS\workspace_feb26\EntrioServer\target>java -jar EntrioServer-0.0.1-SNAPSHOT.jar
---

## 1. DesktopUser

Stores information about desktop users registered in the system.

### Table Name
`desktop_user`

### Columns

| Column Name        | Data Type | Constraints   | Description                        |
|--------------------|-----------|---------------|------------------------------------|
| id                 | INT       | Primary Key   | Unique user identifier             |
| desktop_id         | VARCHAR   | NOT NULL      | Desktop identifier                 |
| desktop_user_name  | VARCHAR   | NOT NULL      | Desktop user name                  |
| created_at         | TIMESTAMP | NOT NULL      | Record creation time               |
| updated_at         | TIMESTAMP | NOT NULL      | Record last update time            |

### Relationships
- One `DesktopUser` can have many:
  - MonthManager records
  - TaskList records
  - EODInOutRecords
  - ReportsGenerationHistory records

---

## 2. MonthManager

Manages month and year information for each desktop user.

### Table Name
`month_manager`

### Columns

| Column Name        | Data Type | Constraints            | Description                        |
|--------------------|-----------|------------------------|------------------------------------|
| id                 | BIGINT    | Primary Key            | Unique identifier                  |
| month_name         | VARCHAR   | NOT NULL               | Month name (e.g., January)         |
| year               | INT       | NOT NULL               | Year                               |
| desktop_user_id    | INT       | Foreign Key, NOT NULL  | Reference to DesktopUser           |
| created_at         | TIMESTAMP | NOT NULL               | Record creation time               |

### Relationships
- Many `MonthManager` records belong to one `DesktopUser`
- One `MonthManager` can have many:
  - TaskList records
  - EODInOutRecords

---

## 3. TaskList

Stores tasks assigned to users for a specific month.

### Table Name
`task_list`

### Columns

| Column Name          | Data Type     | Constraints            | Description                        |
|----------------------|---------------|------------------------|------------------------------------|
| id                   | BIGINT        | Primary Key            | Unique task identifier             |
| desktop_user_id      | INT           | Foreign Key, NOT NULL  | Reference to DesktopUser           |
| month_manager_id     | BIGINT        | Foreign Key, NOT NULL  | Reference to MonthManager          |
| task                 | VARCHAR(1000) | NOT NULL               | Task description                   |
| assigned_by          | VARCHAR       |                        | Assigned by                        |
| assigned_on          | TIMESTAMP     |                        | Assigned timestamp                |
| status               | VARCHAR       | NOT NULL               | Task status                        |
| status_updated_on    | TIMESTAMP     |                        | Status updated time               |
| created_at           | TIMESTAMP     | NOT NULL               | Record creation time               |
| updated_at           | TIMESTAMP     | NOT NULL               | Record last update time            |

### Status Enum
ASSIGNED
ONGOING
PENDING
COMPLETED
CANCELLED





---

## 4. EODInOutRecords

Tracks daily attendance and work details.

### Table Name
`eod_in_out_records`

### Columns

| Column Name          | Data Type     | Constraints            | Description                        |
|----------------------|---------------|------------------------|------------------------------------|
| id                   | BIGINT        | Primary Key            | Unique identifier                  |
| desktop_user_id      | INT           | Foreign Key, NOT NULL  | Reference to DesktopUser           |
| month_manager_id     | BIGINT        | Foreign Key, NOT NULL  | Reference to MonthManager          |
| time_in              | TIME          |                        | Login time                         |
| time_out             | TIME          |                        | Logout time                        |
| attendance_date      | DATE          | NOT NULL               | Attendance date                    |
| attendance_status    | VARCHAR       | NOT NULL               | Attendance status                  |
| todays_work          | VARCHAR(1000) |                        | Work done for the day              |
| assigned_by          | VARCHAR       |                        | Assigned by                        |
| remarks              | VARCHAR(1000) |                        | Remarks                            |
| created_at           | TIMESTAMP     | NOT NULL               | Record creation time               |
| updated_at           | TIMESTAMP     | NOT NULL               | Record last update time            |

### Attendance Status Enum


---

## 5. ReportsGenerationHistory

Maintains history of report generation per user and month.

### Table Name
`reports_generation_history`

### Columns

| Column Name           | Data Type | Constraints            | Description                        |
|-----------------------|-----------|------------------------|------------------------------------|
| id                    | BIGINT    | Primary Key            | Unique identifier                  |
| desktop_user_id       | INT       | Foreign Key, NOT NULL  | Reference to DesktopUser           |
| for_month             | INT       | NOT NULL               | Report month                       |
| for_year              | INT       | NOT NULL               | Report year                        |
| report_generated_at   | TIMESTAMP | NOT NULL               | Report generated time              |

---

## Entity Relationship Summary

- **DesktopUser**
  - 1 → * MonthManager
  - 1 → * TaskList
  - 1 → * EODInOutRecords
  - 1 → * ReportsGenerationHistory

- **MonthManager**
  - * → 1 DesktopUser
  - 1 → * TaskList
  - 1 → * EODInOutRecords

---

## Notes

- All enums are stored as strings.
- All timestamps use server local time.
- Lazy loading is recommended for relationships.
- Indexes should be added on foreign keys and date fields for performance.

---

**Entrio Server – Database Documentation**

