package com.example.home.todo_ap;

import java.io.Serializable;
import java.util.Objects;
    public class TaskInfo implements Serializable,Comparable<TaskInfo> {
        private static final long serialVersionUID=4321;
        private String taskName;
        private String date;
        private String time;
        private String comment;
        private Priority priority;


        public TaskInfo(String taskName, Priority priority) {
            this.taskName = taskName;
            this.priority = priority;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Priority getPriority() {
            return priority;
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TaskInfo taskInfo = (TaskInfo) o;
            return Objects.equals(taskName, taskInfo.taskName) &&
                    Objects.equals(date, taskInfo.date) &&
                    Objects.equals(time, taskInfo.time) &&
                    Objects.equals(comment, taskInfo.comment) &&
                    priority == taskInfo.priority;
        }

        @Override
        public int hashCode() {
            return Objects.hash(taskName, date, time, comment, priority);
        }

        @Override
        public int compareTo(TaskInfo o) {
            if(o.date.compareTo(this.date) == 0)
            {
                if(this.priority == o.priority) return 0;
                if(this.priority == Priority.high) return 1;
                if(this.priority == Priority.low) return -1;
                if(o.priority == Priority.high) return -1;
                if(o.priority == Priority.low) return 1;

            }
            return -1 * o.date.compareTo(this.date);
        }
    }

