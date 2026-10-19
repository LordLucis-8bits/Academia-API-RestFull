import { cookies } from "next/headers";

interface GymClass {
    id: string;
    typeClass: string;
    schedule: string;
    instructorId: string;
    studentsLimit: number;
    classStatus: string;
    enrolledCount: number;
}

async function fetchAvailableClasses(): Promise<GymClass[]> {
    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value;

    const response = await fetch("http://localhost:8080/students/classes/available", {
        headers: { Authorization: `Bearer ${token}` },
        cache: "no-store"
    });

    return response.json();
}

export default async function AvailableClassesPage() {
    const classes = await fetchAvailableClasses();
}
