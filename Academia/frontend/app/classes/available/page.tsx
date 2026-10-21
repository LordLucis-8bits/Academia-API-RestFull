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

    return (
        <div>
            <h1>Classes Available</h1>
            {classes.map((classe) => (
                <div key={classe.id}>
                    <div>{classe.typeClass}</div>
                    <div>{classe.schedule}</div>
                    <div>{classe.enrolledCount} of {classe.studentsLimit}</div>
                </div>
            ))}
        </div>
    );
}
