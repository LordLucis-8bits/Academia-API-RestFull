import { cookies } from "next/headers";
import { redirect } from "next/navigation";

export interface Instructor {
    id: string;
    name: string;
    age: number;
    email: string;
    specialty: string;
}

export async function getInstructorInfo(): Promise<Instructor> {
    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value;

    if (!token) {
        redirect("/login");
    }

    const response = await fetch("http://localhost:8080/instructors/me", {
        headers: { Authorization: `Bearer ${token}` },
        cache: "no-store"
    });

    if (!response.ok) {
        redirect("/login");
    }
    
    return response.json();
}
