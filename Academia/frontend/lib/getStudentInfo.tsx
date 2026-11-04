import { cookies } from "next/headers";
import { redirect } from "next/navigation";

export interface Student {
    id: string;
    name: string;
    age: number;
    email: string;
    planType: string;
    planStart: string;
    planEnd: string;
    planActive: boolean;
}

export async function getStudentInfo(): Promise<Student> {
    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value;

    if (!token) {
        redirect("/login");
    }

    const response = await fetch("http://localhost:8080/students/me", {
        headers: { Authorization: `Bearer ${token}` },
        cache: "no-store"
    });

    if (!response.ok) {
        redirect("/login");
    }

    return response.json();
}